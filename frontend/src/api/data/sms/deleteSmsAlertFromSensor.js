import { fetchWithAuth } from "../fetchWithAuth";

export const deleteSmsAlertFromSensor = async (alertId) => {
  try {
    const response = await fetchWithAuth(`api/deletesmsalert/${alertId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      }
    });
    return response.ok;
  } catch {
    console.log("error");
  }
};
