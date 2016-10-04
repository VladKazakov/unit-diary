package com.controller;

import com.model.Diary;
import com.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс-контроллер, который будет замапен на адрес /diary для обработки запросов с клиента.
 *
 * @autor Vlad Kazakov
 * @version 1.0
  */
@Controller
@RequestMapping("/diary")
public class DiaryController {

    //Внедряем зависимость с помощью spring аннотации @Autowired и вызывает соответствующие методы у сервиса.
    @Autowired
    private DiaryService diaryService;

    /**
     * Метод присылает GET-запрос записей БД
     * @return записи из БД
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List getDiary() {
        return diaryService.getDiary();
    }

    /**
     * Метод присылает POST-запрос записей БД {@link ExtResult}
     * @param diary
     * @return добавляет запись в БД
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ExtResult setDiary(@RequestBody Diary diary) {
        return new ExtResult(diaryService.add(diary), diary);
    }

    /**
     * Метод присылает DELETE-запрос записей БД
     * @param diary
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delDiary(@RequestBody Diary diary) {
        diaryService.delete(diary);
    }

    /**
     * Метод присылает PUT-запрос записей БД
     * @param diary
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void updateDiary(@RequestBody Diary diary) {
        diaryService.update(diary);
    }
}