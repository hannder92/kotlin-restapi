package com.medco.restapi.web

import com.medco.restapi.business.IPersonaBusiness
import com.medco.restapi.exception.BusinessException
import com.medco.restapi.model.Persona
import com.medco.restapi.utils.Constants
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_PERSONAS)
class PersonaRestController {

  @Autowired
  lateinit var personaBusiness: IPersonaBusiness

  @GetMapping
  fun list(): ResponseEntity<List<Persona>> {
    return try {
      ResponseEntity(personaBusiness.list(), HttpStatus.OK)
    } catch (e: BusinessException) {
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @GetMapping("/{id}")
  fun get(@PathVariable("id") id: Long): ResponseEntity<Persona> {
    return try {
      ResponseEntity(personaBusiness.load(id), HttpStatus.OK)
    } catch (e: BusinessException) {
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    } catch (e: NotFoundException) {
      ResponseEntity(HttpStatus.NOT_FOUND)
    }
  }

  @PostMapping
  fun save(@RequestBody persona: Persona): ResponseEntity<Persona> {
    return try {
      val responseHeader = HttpHeaders()
      responseHeader.set("location", Constants.URL_BASE_PERSONAS + "/${persona.id}")
      ResponseEntity(personaBusiness.save(persona), HttpStatus.CREATED)
    } catch (e: BusinessException) {
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @PutMapping
  fun update(@RequestBody persona: Persona): ResponseEntity<Persona> {
    return try {
      personaBusiness.save(persona)
      ResponseEntity(HttpStatus.OK)
    } catch (e: BusinessException) {
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }

  @DeleteMapping("/{id}")
  fun deletePersona(@PathVariable("id") id: Long): ResponseEntity<Any> {
    return try {
      personaBusiness.remove(id)
      ResponseEntity(HttpStatus.OK)
    } catch (e: BusinessException) {
      ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
    } catch (e: NotFoundException) {
      ResponseEntity(HttpStatus.NOT_FOUND)
    }
  }

}