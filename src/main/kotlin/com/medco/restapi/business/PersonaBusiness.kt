package com.medco.restapi.business

import com.medco.restapi.dao.PersonaRepository
import com.medco.restapi.exception.BusinessException
import com.medco.restapi.model.Persona
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
class PersonaBusiness : IPersonaBusiness {

  @Autowired
  lateinit var personaRepository: PersonaRepository

  @Throws(BusinessException::class)
  override fun list(): List<Persona> {
    try {
      return personaRepository.findAll()
    } catch (e: Exception) {
      throw BusinessException(e.message)
    }
  }

  override fun load(idPersona: Long): Persona {
    TODO("Not yet implemented")
  }

  override fun save(persona: Persona): Persona {
    TODO("Not yet implemented")
  }

  override fun remove(idPersona: Long) {
    TODO("Not yet implemented")
  }
}