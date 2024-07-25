package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final Provider<MyLogger> objectProvider;

    public void logic(String id) {
        MyLogger myLogger = objectProvider.get();
        myLogger.log("service id = " + id);
    }
}
