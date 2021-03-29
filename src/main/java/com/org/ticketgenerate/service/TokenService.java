package com.org.ticketgenerate.service;

import ch.qos.logback.core.net.server.Client;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

@Service

public class TokenService {
    SecretClient client = new SecretClientBuilder()
            .vaultUrl("https://<your Key Vault name>.vault.azure.net")
            .credential(new DefaultAzureCredentialBuilder().build())
            .buildClient();
}
