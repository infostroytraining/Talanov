package com.gmail.alexandrtalan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Проверка орфографии
 * <p>
 * Некоторые люди не обращают внимание на орфографию. Например, не пишут новое
 * предложение с заглавной буквы. Или не ставят пробел после знаков препинания.
 * <p>
 * Ваша задача: исправить их ошибки.
 * <p>
 * Что нужно сделать:
 * <p>
 * 1. Каждое новое предложение должно начинаться с заглавной буквы.
 * 2. После знаков препинания (точка и запятая) должны быть пробелы.
 */
public class TextUtils {

    private final static String FIRST_LETTER_SENTENCE_IN_LOWER_CASE = "^([a-zа-я]).+$";
    private final static String MIDDLE_TEXT_FIRST_LETTER_SENTENCE_IN_LOWER_CASE = "^.+\\. ?([a-zа-я]).+$";
    private final static String NO_SPACE_AFTER_DOT = "^.+(\\.\\S).+$";
    private final static String NO_SPACE_AFTER_COMMA = "^.+(,\\S).+$";
    private final static String POINT_IN_THE_END_OF_THE_SENTENCE = "^.+\\..+$";

    public String correctText(String text) {
        StringBuilder inputText = new StringBuilder(text);

        Matcher firstLetterSentenceInLowerCaseMatcher = Pattern
                .compile(FIRST_LETTER_SENTENCE_IN_LOWER_CASE)
                .matcher(inputText);

        Matcher middleTextFirstLetterSentenceInLowerCaseMatcher = Pattern
                .compile(MIDDLE_TEXT_FIRST_LETTER_SENTENCE_IN_LOWER_CASE)
                .matcher(inputText);

        Matcher noSpaceAfterCommaMatcher = Pattern
                .compile(NO_SPACE_AFTER_COMMA)
                .matcher(inputText);

        Matcher noSpaceAfterDotMatcher = Pattern
                .compile(NO_SPACE_AFTER_DOT)
                .matcher(inputText);

        Matcher pointInTheEndOfTheSentenceMatcher = Pattern
                .compile(POINT_IN_THE_END_OF_THE_SENTENCE)
                .matcher(inputText);

        if (firstLetterSentenceInLowerCaseMatcher.find()) {
            int pos = firstLetterSentenceInLowerCaseMatcher.start(1);
            String firstLetter = firstLetterSentenceInLowerCaseMatcher.group(1);
            inputText.replace(pos, pos + 1, firstLetter.toUpperCase());
        }

        if (middleTextFirstLetterSentenceInLowerCaseMatcher.find()) {
            int pos = middleTextFirstLetterSentenceInLowerCaseMatcher.start(1);
            String firstLetter = middleTextFirstLetterSentenceInLowerCaseMatcher.group(1);
            inputText.replace(pos, pos + 1, firstLetter.toUpperCase());
        }

        if (noSpaceAfterCommaMatcher.find()) {
            int pos = noSpaceAfterCommaMatcher.start(1);
            inputText.replace(pos, pos + 1, ", ");
        }

        if (noSpaceAfterDotMatcher.find()) {
            int pos = noSpaceAfterDotMatcher.start(1);
            if (pos != inputText.length()) {
                inputText.replace(pos, pos + 1, ". ");
            }
        }

        if (!pointInTheEndOfTheSentenceMatcher.find()) {
            inputText.append(".");
        }

        return inputText.toString();
    }
}
