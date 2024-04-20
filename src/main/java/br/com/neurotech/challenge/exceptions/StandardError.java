package br.com.neurotech.challenge.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandardError{
	private static final long serialVersionUID = 1L;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd't'HH:mm:ss'Z'", timezone = "GMT")
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

}
