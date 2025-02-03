import { fetchWithAuth } from "../fetchWithAuth";

export const deleteSensorById = async (id) => {
  try {
    const response = await fetchWithAuth(`api/sensors-delete/${id}`, {
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
