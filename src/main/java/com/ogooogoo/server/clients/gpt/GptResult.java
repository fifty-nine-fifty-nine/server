package com.ogooogoo.server.clients.gpt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Getter @Setter
class Arguments {
    @JsonProperty("Breed")
    private String breed;

    @JsonProperty("Adjective")
    private String adjective;

    @Override
    public String toString() {
        return "Arguments{" +
                "breed=" + breed +
                ", adjective=" + adjective +
                '}';
    }
}

@Getter @Setter
class FunctionCall {
    private String name;
    private String arguments; // 원래의 문자열 필드
    private Arguments argumentsObj; // 새로운 객체 필드

    @JsonIgnore // JSON 직렬화/역직렬화에서 무시
    public Arguments getArgumentsObj() {
        if (argumentsObj == null && arguments != null) {
            try {
                // 문자열을 Arguments 객체로 변환
                argumentsObj = new ObjectMapper().readValue(arguments, Arguments.class);
            } catch (Exception e) {
                // 예외 처리
            }
        }
        return argumentsObj;
    }

    @Override
    public String toString() {
        return "FunctionCall{" +
                "name='" + name + '\'' +
                ", arguments='" + arguments + '\'' +
                '}';
    }
}

@Getter @Setter
class Message {
    private String role;
    private String content;

    @JsonProperty("function_call")
    private FunctionCall functionCall;

    @Override
    public String toString() {
        return "Message{" +
                "role='" + role + '\'' +
                ", content='" + content + '\'' +
                ", functionCall=" + functionCall +
                '}';
    }
}

@Getter @Setter
class Choice {
    private int index;
    private Message message;

    // Getters and setters

    @Override
    public String toString() {
        return "Choice{" +
                "index=" + index +
                ", message=" + message +
                '}';
    }
}

@Getter @Setter
@NoArgsConstructor
public class GptResult {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choice> choices;

    // Getters and setters

    @Override
    public String toString() {
        return "GptResult{" +
                "id='" + id + '\'' +
                ", object='" + object + '\'' +
                ", created=" + created +
                ", model='" + model + '\'' +
                ", choices=" + choices +
                '}';
    }

    public String getBreed(){
        Arguments arguments = this.getChoices().get(0).getMessage().getFunctionCall().getArgumentsObj();
        return arguments.getBreed();
    }

    public String getAdjective() {
        Arguments arguments = this.getChoices().get(0).getMessage().getFunctionCall().getArgumentsObj();
        return arguments.getAdjective();
    }

    public GptResult(String breed, String adjective){
        // 새로운 Arguments 객체를 생성하고 설정
        Arguments arguments = new Arguments();
        arguments.setBreed(breed);
        arguments.setAdjective(adjective);

        // 새로운 FunctionCall 객체를 생성하고 설정
        FunctionCall functionCall = new FunctionCall();
        functionCall.setArgumentsObj(arguments);

        // 새로운 Message 객체를 생성하고 설정
        Message message = new Message();
        message.setFunctionCall(functionCall);

        // 새로운 Choice 객체를 생성하고 설정
        Choice choice = new Choice();
        choice.setMessage(message);

        // choices 리스트를 생성하고 설정
        this.choices = Arrays.asList(choice);
    }
}
