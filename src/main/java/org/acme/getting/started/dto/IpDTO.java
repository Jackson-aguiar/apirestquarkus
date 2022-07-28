package org.acme.getting.started.dto;

import javax.enterprise.context.RequestScoped;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequestScoped
public class IpDTO {
	private String ip;
}
