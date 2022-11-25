package com.example.FlightsApplication.controller;

import com.example.FlightsApplication.dto.CheckTicketResponseDto;
import com.example.FlightsApplication.dto.CheckinStatusResponseDto;
import com.example.FlightsApplication.exception.CheckinNotFoundException;
import com.example.FlightsApplication.service.CheckinService;
import com.example.FlightsApplication.service.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ControllerTest {

	@Mock
	private TicketService ticketService;

	@Mock
	private CheckinService checkinService;

	@InjectMocks
	private Controller controller;

	@Test
	public void testCheckTicketController() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(ticketService.checkTicket(any())).thenReturn(true);

		ResponseEntity<CheckTicketResponseDto> responseEntity = controller.checkTicket(212512);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(responseEntity.getBody().getTicketAvailable()).isEqualTo(true);

		when(ticketService.checkTicket(3423522)).thenReturn(false);

		responseEntity = controller.checkTicket(3423522);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(responseEntity.getBody().getTicketAvailable()).isEqualTo(false);
	}

	@Test
	public void testCheckinStatusController() throws CheckinNotFoundException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(checkinService.checkStatus(212512,"124125")).thenReturn(true);

		ResponseEntity<CheckinStatusResponseDto> responseEntity = controller.checkinStatus(212512,"124125");
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(responseEntity.getBody().getCheckinSucceed()).isEqualTo(true);

		when(checkinService.checkStatus(212512,"124125")).thenReturn(false);

		responseEntity = controller.checkinStatus(212512,"124125");
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(responseEntity.getBody().getCheckinSucceed()).isEqualTo(false);
	}
}