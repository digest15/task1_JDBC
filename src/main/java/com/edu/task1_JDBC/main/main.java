package com.edu.task1_JDBC.main;

import com.edu.task1_JDBC.dao.GenericDao;
import com.edu.task1_JDBC.dao.DaoFactory;
import com.edu.task1_JDBC.entity.*;
import com.edu.task1_JDBC.entity.Color;
import com.edu.task1_JDBC.helpers.DateHelper;
import com.edu.task1_JDBC.hsql.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class main {
    public static void main(String[] args) {
        DaoFactory factoryDao = new DaoFactoryHSQL();
        try (Connection connection = factoryDao.getConnection()) {
            Random random = new Random(new Date().getTime());            
            
            //CarService
            GenericDao carServiceDao = factoryDao.getCarServiceDao(connection);
            List<CarService> carServiceList = carServiceDao.readAll();
            if (carServiceList.size() == 0) {
                //1
                CarService carService = new CarService();
                carService.setAddress("г.Иркутск, Александровское шоссе 11 км");
                carService.setName("Александровское");
                carService.setOpeningTime(10);
                carService.setClosingTime(20);
                carServiceList.add(carService);

                //2
                carService = new CarService();
                carService.setAddress("г.Иркутск, ул. Ширямова 10");
                carService.setName("На Ширямва");
                carService.setOpeningTime(9);
                carService.setClosingTime(19);
                carServiceList.add(carService);
                
                carServiceDao.persistAll(carServiceList);
            }

            //Color
            GenericDao colorDao = factoryDao.getColorDao(connection);
            List<Color> colorList = colorDao.readAll();
            if (colorList.size() == 0) {
                //1
                Color color = new Color();
                color.setName("Красный");
                color.setRgb("255 0 0");
                colorList.add(color);

                //2
                color = new Color();
                color.setName("Оранжевый");
                color.setRgb("255 128 0");
                colorList.add(color);

                //3
                color = new Color();
                color.setName("Синий");
                color.setRgb("0 0 255");
                colorList.add(color);

                //4
                color = new Color();
                color.setName("Фиолетовый");
                color.setRgb("128 0 255");
                colorList.add(color);

                //5
                color = new Color();
                color.setName("Белый");
                color.setRgb("255 255 255");
                colorList.add(color);

                //6
                color = new Color();
                color.setName("Черный");
                color.setRgb("0 0 0");
                colorList.add(color);

                //7
                color = new Color();
                color.setName("Серый");
                color.setRgb("128 128 128");
                colorList.add(color);

                //8
                color = new Color();
                color.setName("Зеленый");
                color.setRgb("0 255 0");
                colorList.add(color);
                
                colorDao.persistAll(colorList);
            }

            //Mark
            GenericDao markDao = factoryDao.getMarkDao(connection);
            List<Mark> markList = markDao.readAll();
            if (markList.size() == 0) {
                //1
                Mark mark = new Mark();
                mark.setManufacturer("Audi");
                markList.add(mark);

                //2
                mark = new Mark();
                mark.setManufacturer("BMW");
                markList.add(mark);

                //3
                mark = new Mark();
                mark.setManufacturer("Chevrolet");
                markList.add(mark);

                //4
                mark = new Mark();
                mark.setManufacturer("Ford");
                markList.add(mark);

                //5
                mark = new Mark();
                mark.setManufacturer("Honda");
                markList.add(mark);

                //6
                mark = new Mark();
                mark.setManufacturer("Hyundai");
                markList.add(mark);

                //7
                mark = new Mark();
                mark.setManufacturer("Lexus");
                markList.add(mark);

                //8
                mark = new Mark();
                mark.setManufacturer("Kia");
                markList.add(mark);

                //9
                mark = new Mark();
                mark.setManufacturer("Mazda");
                markList.add(mark);

                //10
                mark = new Mark();
                mark.setManufacturer("Mercedes-Benz");
                markList.add(mark);

                //11
                mark = new Mark();
                mark.setManufacturer("Mitsubishi");
                markList.add(mark);

                //12
                mark = new Mark();
                mark.setManufacturer("Nissan");
                markList.add(mark);

                //13
                mark = new Mark();
                mark.setManufacturer("Opel");
                markList.add(mark);

                //14
                mark = new Mark();
                mark.setManufacturer("Toyota");
                markList.add(mark);

                //15
                mark = new Mark();
                mark.setManufacturer("Лада");
                markList.add(mark);

                //16
                mark = new Mark();
                mark.setManufacturer("Скания");
                markList.add(mark);

                //17
                mark = new Mark();
                mark.setManufacturer("Камаз");
                markList.add(mark);

                //18
                mark = new Mark();
                mark.setManufacturer("Татра");
                markList.add(mark);

                //19
                mark = new Mark();
                mark.setManufacturer("Маз");
                markList.add(mark);

                //20
                mark = new Mark();
                mark.setManufacturer("Ман");
                markList.add(mark);

                //21
                mark = new Mark();
                mark.setManufacturer("Лиаз");
                markList.add(mark);

                //22
                mark = new Mark();
                mark.setManufacturer("ГАЗ");
                markList.add(mark);

                markDao.persistAll(markList);
                
            }

            //Car
            GenericDao carDao = factoryDao.getCarDao(connection, factoryDao);
            List<Car> carList = carDao.readAll();
            if (carList.size() == 0) {
                Car car;
                for (int i=0;i <=10;i++) {
                    car = new Car();
                    car.setNamePicking("Люкс");
                    car.setColor(colorList.get(random.nextInt(colorList.size())));
                    car.setNumberPassengerSeats(5);
                    car.setMark(markList.get(random.nextInt(markList.size() - 7))); //Только легковые марки
                    car.setReleaseYear(new Date());
                    car.setVin(String.valueOf(random.nextLong()).substring(19));

                    carList.add(car);
                }
                carDao.persistAll(carList);
            }

            //Bus
            GenericDao busDao = factoryDao.getBusDao(connection, factoryDao);
            List<Bus> busList = busDao.readAll();
            if (busList.size() == 0) {
                Bus bus;
                for (int i = 0; i <= 10; i++) {
                    bus = new Bus();
                    bus.setNamePicking("Люкс");
                    bus.setColor(colorList.get(random.nextInt(colorList.size())));
                    bus.setNumberPassengerSeats(random.nextInt(20) + 20);
                    bus.setNumberPassengerStanding(random.nextInt(20) + 20);
                    bus.setMark(markList.get(random.nextInt(7) + 15)); //Только грузовые и автобусы
                    bus.setReleaseYear(new Date());
                    bus.setVin(String.valueOf(random.nextLong()).substring(19));

                    busList.add(bus);
                }
                busDao.persistAll(busList);
            }

            //Truck
            GenericDao truckDao = factoryDao.getTruckDao(connection, factoryDao);
            List<Truck> truckList = truckDao.readAll();
            if (truckList.size() == 0) {
                Truck truck;
                for (int i=0;i <=10;i++) {
                    truck = new Truck();
                    truck.setNamePicking("Люкс");
                    truck.setNumberAxle(random.nextInt(6) + 2);
                    truck.setColor(colorList.get(random.nextInt(colorList.size())));
                    truck.setMark(markList.get(random.nextInt(7) + 15)); //Только грузовые и автобусы
                    truck.setReleaseYear(new Date());
                    truck.setVin(String.valueOf(random.nextLong()).substring(19));

                    truckList.add(truck);
                }
                truckDao.persistAll(truckList);
            }

            //Mechanic
            GenericDao mechanicDao = factoryDao.getMehanicDao(connection);
            List<Mechanic> mechanicList = mechanicDao.readAll();
            if (mechanicList.size() == 0) {
                //1
                Mechanic mechanic = new Mechanic();
                mechanic.setName("Иван");
                mechanic.setLastName("Иванов");
                mechanic.setSalary(new BigDecimal(10000));
                mechanicList.add(mechanic);

                //2
                mechanic = new Mechanic();
                mechanic.setName("Петя");
                mechanic.setLastName("Петров");
                mechanic.setSalary(new BigDecimal(20000));
                mechanicList.add(mechanic);

                //3
                mechanic = new Mechanic();
                mechanic.setName("Саша");
                mechanic.setLastName("Сидоров");
                mechanic.setSalary(new BigDecimal(30000));
                mechanicList.add(mechanic);

                //4
                mechanic = new Mechanic();
                mechanic.setName("Дарма");
                mechanic.setLastName("Бельдыев");
                mechanic.setSalary(new BigDecimal(20000));
                mechanicList.add(mechanic);

                //5
                mechanic = new Mechanic();
                mechanic.setName("Вася");
                mechanic.setLastName("Пупкин");
                mechanic.setSalary(new BigDecimal(5000));
                mechanicList.add(mechanic);

                //6
                mechanic = new Mechanic();
                mechanic.setName("Миша");
                mechanic.setLastName("Малкин");
                mechanic.setSalary(new BigDecimal(30000));
                mechanicList.add(mechanic);

                //7
                mechanic = new Mechanic();
                mechanic.setName("Миша");
                mechanic.setLastName("Палкин");
                mechanic.setSalary(new BigDecimal(40000));
                mechanicList.add(mechanic);

                //8
                mechanic = new Mechanic();
                mechanic.setName("Федор");
                mechanic.setLastName("Федоров");
                mechanic.setSalary(new BigDecimal(50000));
                mechanicList.add(mechanic);

                //9
                mechanic = new Mechanic();
                mechanic.setName("Степан");
                mechanic.setLastName("Степанов");
                mechanic.setSalary(new BigDecimal(25000));
                mechanicList.add(mechanic);

                //10
                mechanic = new Mechanic();
                mechanic.setName("Кеша");
                mechanic.setLastName("Кротов");
                mechanic.setSalary(new BigDecimal(35000));
                mechanicList.add(mechanic);

                //save
                mechanicDao.persistAll(mechanicList);
            }


            ArrayList<Machine> machineList = new ArrayList<>();
            machineList.addAll(carList);
            machineList.addAll(busList);
            machineList.addAll(truckList);

            GenericDao repairDao = factoryDao.getRepairDao(connection, factoryDao);
            List<Repair> repairList = repairDao.readAll();
            if (repairList.size() == 0) {
                Repair repair;
                for (int i=0; i < 100; i++) {
                    repair = new Repair();
                    repair.setDateTime(DateHelper.randomDate());
                    repair.setCarServise(carServiceList.get(random.nextInt(2)));
                    repair.setMechanic(mechanicList.get(random.nextInt(mechanicList.size())));
                    repair.setMachine(machineList.get(random.nextInt(machineList.size())));
                    repair.setAmount(new BigDecimal(random.nextDouble()));

                    repairList.add(repair);
                }
                repairDao.persistAll(repairList);
            }

        } catch (Exception e) {
            System.out.println("Опаньки ошибка: " + e);
        }
    }
}
