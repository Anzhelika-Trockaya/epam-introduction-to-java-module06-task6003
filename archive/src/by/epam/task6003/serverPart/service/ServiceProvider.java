package by.epam.task6003.serverPart.service;

import by.epam.task6003.serverPart.service.impl.IdServiceImpl;
import by.epam.task6003.serverPart.service.impl.StudentsServiceImpl;
import by.epam.task6003.serverPart.service.impl.UsersServiceImpl;

public class ServiceProvider {
    private final static ServiceProvider instance = new ServiceProvider();

    private final IdService idService;
    private final UsersService usersService;
    private final StudentsService studentsService;

    private ServiceProvider(){
        idService=new IdServiceImpl();
        usersService=new UsersServiceImpl();
        studentsService=new StudentsServiceImpl();
    }

    public static ServiceProvider getInstance(){
        return instance;
    }

    public IdService getIdService() {
        return idService;
    }

    public UsersService getUsersService() {
        return usersService;
    }

    public StudentsService getStudentsService() {
        return studentsService;
    }
}
