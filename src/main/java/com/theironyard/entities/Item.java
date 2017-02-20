package com.theironyard.entities;

import com.theironyard.EventChannel.StoryOutput;
import com.theironyard.EventChannel.things.interfaces.IUsable;
import com.theironyard.EventChannel.things.interfaces.ITakable;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "items")
public class Item implements IUsable, ITakable {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String type;
}

