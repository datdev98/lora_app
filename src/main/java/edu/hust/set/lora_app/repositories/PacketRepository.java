package edu.hust.set.lora_app.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.hust.set.lora_app.models.Packet;

public interface PacketRepository extends CrudRepository<Packet, Integer> {
    
}