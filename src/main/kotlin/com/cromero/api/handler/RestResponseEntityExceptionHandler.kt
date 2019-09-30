package com.cromero.api.handler

import com.cromero.api.controller.ResponseDTO
import com.cromero.api.service.UserNotFoundException
import mu.KotlinLogging
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestResponseEntityExceptionHandler(private val messageSource: MessageSource) : ResponseEntityExceptionHandler() {

    private val LOGGER = KotlinLogging.logger {}


    @ExceptionHandler(UserNotFoundException::class)
    fun handleControllerException(ex: UserNotFoundException, request: WebRequest): ResponseEntity<Any> {
        LOGGER.error("$ex.message" )
        val responseDTO = ResponseDTO(status = HttpStatus.NOT_FOUND.value(), data = "User not found")
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO)
    }


    /**
     * Handle default runtime exceptions
     *
     * @param ex
     * @param request
     * @return default error message
     */
    @ExceptionHandler(RuntimeException::class)
    fun handleInternal(ex: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        LOGGER.error(" Unhandled runtime exception was thrown. With message: $ex.message")
        val responseDTO = ResponseDTO(status = 500, data = "Server Error")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO)
    }

}
