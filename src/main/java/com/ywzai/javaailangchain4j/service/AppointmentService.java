package com.ywzai.javaailangchain4j.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ywzai.javaailangchain4j.entity.Appointment;
import org.springframework.stereotype.Service;


public interface AppointmentService extends IService<Appointment> {

    Appointment getOne(Appointment appointment);
}
