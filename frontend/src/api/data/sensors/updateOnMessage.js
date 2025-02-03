import { fetchWithAuth } from "../fetchWithAuth";

export const updateOnMessage = async (sensorId, onMessage) => {
  try {
    const response = await fetchWithAuth(`api/sensors/editOnMessage/${sensorId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: onMessage
    });
    return response.ok;
  } catch (error) {
    console.error("Error:", error);
  }
};
