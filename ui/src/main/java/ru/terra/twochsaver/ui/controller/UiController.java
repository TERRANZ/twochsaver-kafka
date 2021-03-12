package ru.terra.twochsaver.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.terra.twochsaver.shared.entity.TwochThread;
import ru.terra.twochsaver.ui.exception.AlreadyAddedException;
import ru.terra.twochsaver.ui.service.UiService;

import java.util.List;

@Controller
@RequestMapping("/tws")
public class UiController {
    private final UiService uiService;

    public UiController(UiService uiService) {
        this.uiService = uiService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Integer postObject(@RequestBody String url) throws AlreadyAddedException {
        return uiService.startDownload(url);
    }

    @RequestMapping(value = "/threads", method = RequestMethod.GET)
    public List<TwochThread> getThreads() {
        return uiService.getThreads();
    }
}
