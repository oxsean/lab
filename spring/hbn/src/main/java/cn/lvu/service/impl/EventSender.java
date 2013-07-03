/*
 * Project:  onemap
 * Module:   onemap-common
 * File:     EventSender.java
 * Modifier: xyang
 * Modified: 2013-06-21 21:49
 *
 * Copyright (c) 2013 Gtmap Ltd. All Rights Reserved.
 *
 * Copying of this document or code and giving it to others and the
 * use or communication of the contents thereof, are forbidden without
 * expressed authority. Offenders are liable to the payment of damages.
 * All rights reserved in the event of the grant of a invention patent or the
 * registration of a utility model, design or code.
 */

package cn.lvu.service.impl;

import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:oxsean@gmail.com">sean yang</a>
 * @version V1.0, 13-6-4
 */
public class EventSender implements PostInsertEventListener, PostUpdateEventListener, PostDeleteEventListener, PostCollectionRecreateEventListener, PostCollectionRemoveEventListener, PostCollectionUpdateEventListener, InitializingBean {
    private static final long serialVersionUID = 8907447538547563579L;

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void onPostInsert(PostInsertEvent event) {
        System.out.println(event);
    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        System.out.println(event);
    }

    @Override
    public void onPostDelete(PostDeleteEvent event) {
        System.out.println(event);
    }

    @Override
    public void onPostRecreateCollection(PostCollectionRecreateEvent event) {
        System.out.println(event);
    }

    @Override
    public void onPostRemoveCollection(PostCollectionRemoveEvent event) {
        System.out.println(event);
    }

    @Override
    public void onPostUpdateCollection(PostCollectionUpdateEvent event) {
        System.out.println(event);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        EventListenerRegistry registry = ((SessionFactoryImplementor) sessionFactory).getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(this);
        registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(this);
        registry.getEventListenerGroup(EventType.POST_DELETE).appendListener(this);
        registry.getEventListenerGroup(EventType.POST_COLLECTION_RECREATE).appendListener(this);
        registry.getEventListenerGroup(EventType.POST_COLLECTION_REMOVE).appendListener(this);
        registry.getEventListenerGroup(EventType.POST_COLLECTION_UPDATE).appendListener(this);
    }
}
