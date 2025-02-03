const apiUrl = process.env.REACT_APP_API_URL || "http://default-url.com";

import { useEffect, useState } from "react";
import { fetchWithAuth } from "../fetchWithAuth";

const fetchYearChartData = async () => {
  try {
    const response = await fetchWithAuth(`api/chart-data/year`);

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
      labels: ["IAN", "FEB", "MAR", "APR", "MAI", "IUN", "IUL", "AUG", "SEP", "OCT", "NOI", "DEC"],
      datasets: { label: "Alerte", data: [10, 10, 10, 22, 50, 10, 40, 10, 40, 23, 22, 34] }
    };
  }
};

const useYearChartData = () => {
  const [yearChartData, setYearChartData] = useState({
    labels: [],
    datasets: []
  });

  useEffect(() => {
    const loadChartData = async () => {
      const chartData = await fetchYearChartData();
      setYearChartData(chartData);
    };

    loadChartData();
  }, []);
  return { yearChartData };
};

export default useYearChartData;
