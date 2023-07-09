package com.upgrad.bookmyconsultation.service;

import com.upgrad.bookmyconsultation.entity.Appointment;
import com.upgrad.bookmyconsultation.exception.InvalidInputException;
import com.upgrad.bookmyconsultation.exception.ResourceUnAvailableException;
import com.upgrad.bookmyconsultation.exception.SlotUnavailableException;
import com.upgrad.bookmyconsultation.repository.AppointmentRepository;
import com.upgrad.bookmyconsultation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import static com.upgrad.bookmyconsultation.util.ValidationUtils.validate;

@Service
@RequiredArgsConstructor
public class AppointmentService {
	//mark it autowired
	//create an instance of AppointmentRepository called appointmentRepository
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private UserRepository userRepository;

	//create a method named appointment with the return type of String and parameter of type Appointment
	//declare exceptions 'SlotUnavailableException' and 'InvalidInputException'
		//validate the appointment details using the validate method from ValidationUtils class
		//find if an appointment exists with the same doctor for the same date and time
		//if the appointment exists throw the SlotUnavailableException
		//save the appointment details to the database
		//return the appointment id
	public String appointment(Appointment appointment) throws SlotUnavailableException, InvalidInputException {
		validate(appointment);
		Appointment existingAppointment = appointmentRepository.findByDoctorIdAndTimeSlotAndAppointmentDate(appointment.getDoctorId(), appointment.getTimeSlot(), appointment.getAppointmentDate());

		if(!(existingAppointment == null)) {
			throw new SlotUnavailableException();
		}

		Appointment savedAppointment = appointmentRepository.save(appointment);

		return savedAppointment.getAppointmentId();
	}

	//create a method getAppointment of type Appointment with a parameter name appointmentId of type String
		//Use the appointmentId to get the appointment details
		//if the appointment exists return the appointment
		//else throw ResourceUnAvailableException
		//tip: use Optional.ofNullable(). Use orElseThrow() method when Optional.ofNullable() throws NULL
	public Appointment getAppointment(String appointmentId) {
		Appointment appointmentDetails = Optional.ofNullable(appointmentRepository.findByAppointmentId(appointmentId)).orElseThrow();

		if(appointmentDetails == null) {
			throw new ResourceUnAvailableException();
		}

		return appointmentDetails;
	}

	public List<Appointment> getAppointmentsForUser(String userId) {
		return appointmentRepository.findByUserId(userId);
	}
}
