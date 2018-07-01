/**
 * Copyright (C) 2018, Justin Nguyen
 */
package com.justin.fota;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justin.EnergyGateway;
import com.justin.transmission.dto.FotaDto;

/**
 * @author tuan3.nguyen@gmail.com
 */
public abstract class FotaHandler implements IMqttMessageListener {
  protected final EnergyGateway gateway;
  protected boolean upgradeInProgress;

  protected FotaHandler(final EnergyGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public void messageArrived(final String topic, final MqttMessage message) throws Exception {
    // Upgrade package is not for this gateway.
    final ObjectMapper jsonMapper = new ObjectMapper();
    final FotaDto fotaInfo = jsonMapper.readValue(message.getPayload(), FotaDto.class);
    if (!fotaInfo.getGatewayId().equals(fotaInfo.getGatewayId())) {
      return;
    }

    synchronized (this) {
      // Upgrade already in progress
      if (upgradeInProgress) {
        return;
      }

      upgradeInProgress = true;
      startUpgrade(fotaInfo);
    }
  }

  protected abstract void startUpgrade(FotaDto fotaInfo) throws Exception;
}
