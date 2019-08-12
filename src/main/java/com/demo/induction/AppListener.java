package com.demo.induction;

import com.demo.induction.utils.Utils;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AppListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {


    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationStartedEvent) {
        ConfigurableEnvironment ac = applicationStartedEvent.getEnvironment();

        Utils.printDecorator("ACTIVE ENV");
        List<MapPropertySource> propertySources = new ArrayList<>();

        ac.getPropertySources().forEach(it -> {
            if (it instanceof MapPropertySource && it.getName().contains("applicationConfig")) {
                propertySources.add((MapPropertySource) it);
            }
        });

        propertySources.stream()
                .map(propertySource -> propertySource.getSource().keySet())
                .flatMap(Collection::stream)
                .distinct()
                .sorted()
                .forEach(key -> {
                    try {
                        System.out.println(key + "=" + ac.getProperty(key));
                    } catch (Exception e) {
                        System.out.printf("{} -> {}", key, e.getMessage());
                    }
                });

        Utils.printDecorator("");
    }
}
