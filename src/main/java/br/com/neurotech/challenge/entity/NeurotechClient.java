package br.com.neurotech.challenge.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity(name = "NeurotechClient")
@Table(name = "tb_neurotech_client")
public class NeurotechClient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer age;
	private Double income;
}