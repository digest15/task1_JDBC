package com.edu.task1_JDBC.main;

import com.edu.task1_JDBC.dao.GenericDao;
import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.entity.*;
import com.edu.task1_JDBC.entity.Color;
import com.edu.task1_JDBC.hsql.*;

import java.awt.*;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class main {
    public static void main(String[] args) {
        Random random = new Random(new Date().getTime());

        DaoFactory daoFactory = new DaoFactoryHSQL();
        try (Connection connection = daoFactory.getConnection()) {
            GenericDao carServiceDao = daoFactory.getCarServiceDao(connection);
            List<CarService> carServicelist = carServiceDao.readAll();
            if (carServicelist.size() == 0) {
                //1
                CarService carService = new CarService();
                carService.setAddress("г.Иркутск, Александровское шоссе 11 км");
                carService.setName("Александровское");
                carService.setOpeningTime(10);
                carService.setClosingTime(20);
                carServiceDao.persist(carService);

                //2
                carService = new CarService();
                carService.setAddress("г.Иркутск, ул. Ширямова 10");
                carService.setName("На Ширямва");
                carService.setOpeningTime(9);
                carService.setClosingTime(19);
                carServiceDao.persist(carService);
            } else {
                for (CarService carService : carServicelist) {
                    Object cs = (CarService)carServiceDao.readById(carService.getId());
                    carServiceDao.update(carService);
                    carServiceDao.delete(carService);
                }
            }

            GenericDao colorDao = daoFactory.getColorDao(connection);
            List<Color> colorList = colorDao.readAll();
            if (colorList.size() == 0) {
                //1
                Color color = new Color();
                color.setName("Красный");
                color.setRgb("255 0 0");
                colorDao.persist(color);

                //2
                color = new Color();
                color.setName("Оранжевый");
                color.setRgb("255 128 0");
                colorDao.persist(color);
            }else {
                for (Color color : colorList) {
                    Color cl = (Color) colorDao.readById(color.getId());
                    colorDao.update(color);
                    colorDao.delete(color);
                }
            }

            GenericDao markDao = daoFactory.getMarkDao(connection);
            List<Mark> markList = markDao.readAll();
            if (markList.size() == 0) {
                //1
                Mark mark = new Mark();
                mark.setManufacturer("Audi");
                markDao.persist(mark);

                //2
                mark = new Mark();
                mark.setManufacturer("BMW");
                markDao.persist(mark);
            }else {
                for (Mark mark : markList) {
                    Mark mr = (Mark) markDao.readById(mark.getId());
                    markDao.update(mark);
                    markDao.delete(mark);
                }
            }

            GenericDao CarDao = daoFactory.getCarDao(connection);
            List<Car> CarList = CarDao.readAll();
            if (CarList.size() == 0) {
                for (int i=0;i <=3;i++) {
                    Car car = new Car();
                    car.setNamePicking("Люкс");
                    //car.setColor(new Color());
                    car.setNumberPassengerSeats(5);
                    //car.setMark(new Mark());
                    car.setReleaseYear(new Date());
                    car.setVin(String.valueOf(random.nextLong()).substring(19));
                    CarDao.persist(car);
                }
            }else {
                for (Machine machine : CarList) {
                    Machine cr = (Machine) CarDao.readById(machine.getId());
                    CarDao.update(machine);
                    CarDao.delete(machine);
                }
            }

        } catch (Exception e) {
            System.out.println("Опаньки ошибка: " + e);
        }
    }
}
