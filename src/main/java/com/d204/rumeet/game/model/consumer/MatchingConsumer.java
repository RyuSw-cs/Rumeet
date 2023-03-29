package com.d204.rumeet.game.model.consumer;

import com.d204.rumeet.game.model.dto.GameDto;
import com.d204.rumeet.tools.MatchingTool;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MatchingConsumer {
    // 일번
    // game stsart 시키는 consumer
    // matching topic 0 ~ 11 구독

    private final MatchingTool matchingTool;

    @KafkaListener(topicPattern = "rumeet\\.game\\.matching\\..*", groupId = "*")
    public void listenMatching(String message) {
        GameDto info = new Gson().fromJson(message, GameDto.class);
        matchingTool.doMatching(info);
    }

    @KafkaListener(topics = "rumeet.game.cancel", groupId = "game")
    public void listenCancle(String message) {
        GameDto info = new Gson().fromJson(message, GameDto.class);
        matchingTool.doCancel(info);
    }
}