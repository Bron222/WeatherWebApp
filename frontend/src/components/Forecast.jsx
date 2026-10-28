import { describeWeather } from '../utils/weatherCodes'

function dayName(isoDate, index) {
  if (index === 0) return 'Today'
  // Adding a time makes the browser read the date as local, not UTC (which can shift it a day).
  return new Date(`${isoDate}T00:00`).toLocaleDateString(undefined, { weekday: 'short' })
}

export default function Forecast({ days }) {
  return (
    <section className="card" aria-label="7-day forecast">
      <h2>7-day forecast</h2>
      <ol className="forecast">
        {days.map((day, index) => {
          const { label, icon } = describeWeather(day.weatherCode)
          return (
            <li key={day.date}>
              <span className="forecast-day">{dayName(day.date, index)}</span>
              <span className="forecast-icon" title={label} aria-label={label} role="img">
                {icon}
              </span>
              <span className="forecast-rain" title="Chance of precipitation">
                💧 {day.precipitationChance}%
              </span>
              <span className="forecast-temps">
                <strong>{Math.round(day.tempMax)}°</strong>{' '}{Math.round(day.tempMin)}°
              </span>
            </li>
          )
        })}
      </ol>
    </section>
  )
}
