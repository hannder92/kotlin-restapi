package com.medco.restapi

import com.medco.restapi.dao.PersonaRepository
import com.medco.restapi.model.Persona
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SpringBootApplication
class RestapiApplication : CommandLineRunner {

  @Autowired
  lateinit var personaRepository: PersonaRepository

  override fun run(vararg args: String?) {
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val persona1 = Persona(123456, "Pepito", "Perez", LocalDate.parse("24-07-1992", formatter))
    personaRepository.save(persona1)
  }

}

fun main(args: Array<String>) {
  runApplication<RestapiApplication>(*args)
}
