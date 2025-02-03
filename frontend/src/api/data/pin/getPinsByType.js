import { fetchWithAuth } from "../fetchWithAuth";

const DEFAULT_DATA = {
  pins: []
};

export const getPinsByType = async () => {
  try {
    const response = await fetchWithAuth(`api/sensors-pin/`);
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    const data = await response.json();
    return data.sensorspin;
  } catch (error) {
    console.error("There was a problem with the fetch operation:", error);
    return DEFAULT_DATA;
  }
};
