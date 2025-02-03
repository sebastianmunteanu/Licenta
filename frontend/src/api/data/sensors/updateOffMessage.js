import { fetchWithAuth } from "../fetchWithAuth";

export const updateOffMessage = async (sensorId, offMessage) => {
  try {
    const response = await fetchWithAuth(`api/sensors/editOffMessage/${sensorId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: offMessage
    });
    return response.ok;
  } catch (error) {
    console.error("Error:", error);
  }
};
