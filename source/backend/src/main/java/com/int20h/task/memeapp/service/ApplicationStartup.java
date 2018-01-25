package com.int20h.task.memeapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private MemeService memeService;

    @Autowired
    public ApplicationStartup(MemeService memeService) {
        this.memeService = memeService;
    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        memeService.loadMemesFromImgFlip();
    }
}