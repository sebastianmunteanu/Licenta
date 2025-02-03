import { fetchWithAuth } from "../fetchWithAuth";

export const updateStatus = async (sensorId, status) => {
  const action = status === "ONLINE" ? "disable" : "enable";
  try {
    const response = await fetchWithAuth(`api/sensors/${sensorId}/${action}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      }
    });
    return response.ok;
  } catch (error) {
    console.error("Error:", error);
  }
};
