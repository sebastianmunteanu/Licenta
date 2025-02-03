function deleteAlert(alertId) {
    console.log(alertId);
    alertId = parseInt(alertId);
    // Trimite cererea DELETE către server pentru ștergerea alertei cu ID-ul specificat
    fetch(`/deleteAlertFromSensor/${alertId}`, {
        method: 'POST'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Ștergerea alertei a eșuat!');
        }
        // Actualizează interfața utilizatorului după ștergere
       // location.reload(); // sau actualizează doar partea relevantă a interfeței utilizatorului
       window.location.href = '/activesensors';
    })
    .catch(error => {
        console.error('Eroare:', error);
        alert('Ștergerea alertei a eșuat! Vă rugăm să încercați din nou.');
    });
}