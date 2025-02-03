import { fetchWithAuth } from "../fetchWithAuth";

const DEFAULT_SENSORS_ALERTS = {
  sensorAlerts: []
};

export const getSensorsAlerts = async () => {
  try {
    const response = await fetchWithAuth(`api/sensor-alerts-all`);
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    const data = await response.json();
    return data.sensorAlerts;
  } catch (error) {
    console.error("There was a problem with the fetch operation:", error);
    return DEFAULT_SENSORS_ALERTS;
  }
};
