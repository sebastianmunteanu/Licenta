import { useEffect, useState } from "react";
import { fetchWithAuth } from "../fetchWithAuth";

const fetchWeekChartData = async () => {
  try {
    const response = await fetchWithAuth(`api/chart-data/week`);

    if (!response.ok) {
      throw new Error("Network response was not ok");
    }

    const data = await response.json();
    return {
      labels: data.labels,
      datasets: { label: "Alerte", data: data.data }
    };
  } catch (error) {
    console.error("Error fetching chart data:", error);
    return {
      labels: ["L", "M", "M", "J", "V", "S", "D"],
      datasets: { label: "Alerte", data: [10, 20, 10, 22, 50, 10, 40] }
    };
  }
};

const useWeekChartData = () => {
  const [weekChartData, setWeekChartData] = useState({
    labels: [],
    datasets: []
  });

  useEffect(() => {
    const loadChartData = async () => {
      const chartData = await fetchWeekChartData();
      setWeekChartData(chartData);
    };

    loadChartData();
  }, []);
  return { weekChartData };
};

export default useWeekChartData;
