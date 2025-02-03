import { fetchWithAuth } from "../fetchWithAuth";

export const addNewSensor = async (newSensor) => {
  try {
    const response = await fetchWithAuth(`api/sensors-add`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(newSensor)
    });
    return response.ok;
  } catch (error) {
    console.error("Error:", error);
  }
};
