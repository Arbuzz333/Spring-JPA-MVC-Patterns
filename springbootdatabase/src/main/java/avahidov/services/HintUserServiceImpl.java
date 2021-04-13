package avahidov.services;

import avahidov.entities.HintUserEntity;
import avahidov.mappers.EntityMapper;
import avahidov.repositories.HintUserRepository;
import avahidov.uservo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;


@Service
public class HintUserServiceImpl implements HintUserService {

    private static final Logger LOG = LoggerFactory.getLogger(HintUserServiceImpl.class);

    private final EntityMapper entityMapper;

    private final HintUserRepository userRepository;

    public HintUserServiceImpl(
            EntityMapper entityMapper,
            HintUserRepository userRepository) {
        this.entityMapper = entityMapper;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = NullPointerException.class)
    public List<User> createUserList(List<User> userList) {
        var mappedEntityList =  mapUserEntity(userList);
        List<HintUserEntity> savedUserEntityList = saveToDataBaseList(mappedEntityList);
        List<User> users = mappingFuture(savedUserEntityList);
        return users;
    }

    private List<HintUserEntity> mapUserEntity(List<User> userList) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<HintUserEntity> mappedEntityList = new CopyOnWriteArrayList<>();

        userList.forEach(user -> executor.submit(() -> {
            HintUserEntity saved = entityMapper.userToEntity(user);
            mappedEntityList.add(saved);
        }));
        executor.shutdown();

        var done = false;
        try {
            done = executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOG.info("Have all user hints been mapped for try: {}", done);
        LOG.info("Have all user hints been mapped? {}", done);
        LOG.info("Mapped list has size {}.", mappedEntityList.size());
        return mappedEntityList;
    }

    private List<User> mappingFuture(List<HintUserEntity> savedEntityList) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<User> savedList = new ArrayList<>();

        List<Callable<User>> callableList = savedEntityList.stream()
                .map(entity -> (Callable<User>) () -> entityMapper.entityToUserLite(entity))
                .collect(Collectors.toList());

        List<Future<User>> futures = new ArrayList<>();
        try {
            futures = executor.invokeAll(callableList);
        } catch (InterruptedException e) {
            LOG.error("Executor is shutdown: {}, is terminated: {}.", executor.isShutdown(), executor.isTerminated());
            e.printStackTrace();
        }
        futures.forEach(f -> {
            try {
                User user = f.get(1, TimeUnit.SECONDS);
                savedList.add(user);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                LOG.error("Future is done: {}, is canceled: {}.", f.isDone(), f.isCancelled());
                e.printStackTrace();
            }
        });
        executor.shutdown();
        return savedList;
    }

    private List<HintUserEntity> saveToDataBaseList(List<HintUserEntity> userEntityList) {
        List<HintUserEntity> hintUserEntityList = userRepository.saveAll(userEntityList);
        return hintUserEntityList;
    }

    @Override
    @Transactional
    public HintUserEntity saveToDataBase(HintUserEntity userEntity) {
        HintUserEntity saved = userRepository.save(userEntity);
        return saved;
    }

    @Override
    @Transactional
    public HintUserEntity saveToDataBase(User user) {
        HintUserEntity userEntity = entityMapper.userToEntity(user);
        HintUserEntity saved = userRepository.save(userEntity);
        return saved;
    }

    @Override
    @Transactional
    public void deleteFromDataBase(HintUserEntity userEntity) {
        userRepository.delete(userEntity);
    }
}
