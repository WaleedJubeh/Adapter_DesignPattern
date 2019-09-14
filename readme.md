# Adapter_DesignPattern

## Problem:

HosptialA made an agreement with HospitalB to transfer patients and their information between them.
HospitalA and HospitalB both have technical system to manage their systems and the actions that happened on the patients information.
Unfortunately,HospitalA system stores its data in XML file which is different from HospitalB which their system use JSON file.
So they can't connect the both system to interactive with each other,there software engineers were unable to connect them,for that  they call you (Waleed Jubeh)
to connect the two systems and solve the issue.

## Prototype 
I decide to make an adapter to connect both systems.
The adapter is work in both direction so it's convert from XML to JSON and from JSON to XML

The Adapter converts HospitalB to be like HospitalA system which means it's reacted like HospitalA system.

## Tech environment and tools :

  - Intellij IDE
  - JUnit Test
  
## Class diagram for the adapter

![Class diagram](http://waleedjubeh.esy.es/files/adapter.png)

## Result

The prototype is doable and can be applied to connect between the systems
