import { fetchWithAuth } from "../fetchWithAuth";

const DEFAULT_SENSORS_DATA = {
  sensors: []
};

export const fetchSensorsData = async () => {
  try {
    const response = await fetchWithAuth(`api/sensors-data`);
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    const data = await response.json();
    return data.sensors;
  } catch (error) {
    console.error("There was a problem with the fetch operation:", error);
    return DEFAULT_SENSORS_DATA;
  }
};
