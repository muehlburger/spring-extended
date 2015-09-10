package org.springframework.extended.section01.beans.impl

import org.springframework.extended.section01.beans.IRepository
import org.springframework.extended.section01.beans.IService

class ServiceImpl implements IService {

    private IRepository repository

    void setRepository(IRepository repository) {
        this.repository = repository
    }

}
