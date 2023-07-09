package com.upgrad.bookmyconsultation.controller;

import com.upgrad.bookmyconsultation.entity.Appointment;
import com.upgrad.bookmyconsultation.exception.InvalidInputException;
import com.upgrad.bookmyconsultation.exception.SlotUnavailableException;
import com.upgrad.bookmyconsultation.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
	@Autowired
	private AppointmentService appointmentService;

	//create a post method named bookAppointment with return type ResponseEntity
		//method has parameter of type Appointment, use RequestBody Annotation for mapping
		//save the appointment details to the database and save the response from the method used
		//return http response using ResponseEntity
	@PostMapping
	public ResponseEntity<String> bookAppointment(@RequestBody Appointment appointment) throws InvalidInputException, SlotUnavailableException {
		return ResponseEntity.ok(appointmentService.appointment(appointment));
	}

	//create a get method named getAppointment with return type as ResponseEntity
		//method has appointmentId of type String. Use PathVariable annotation to identify appointment using the parameter defined
		//get the appointment details using the appointmentId
		//save the response
		//return the response as a http response
	@GetMapping("/{appointmentId}")
	public ResponseEntity<Appointment> getAppointment(@PathVariable String appointmentId) {
		return ResponseEntity.ok(appointmentService.getAppointment(appointmentId));
	}
}
