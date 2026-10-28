import { describeWeather } from '../utils/weatherCodes'
import { placeDetail } from '../utils/placeDetail'

export default function CurrentConditions({ place, current }) {
  const { label, icon } = describeWeather(current.weatherCode, current.isDay)

  return (
    <section className="card current" aria-label="Current conditions">
      <h2>
        {place.name}
        <span className="place-detail">{placeDetail(place)}</span>
      </h2>

      <div className="current-main">
        <span className="current-icon" aria-hidden="true">
          {icon}
        </span>
        <span className="current-temp">{Math.round(current.temperature)}°C</span>
      </div>
      <p className="current-label">{label}</p>

      <dl className="current-stats">
        <div>
          <dt>Feels like</dt>
          <dd>{Math.round(current.apparentTemperature)}°C</dd>
        </div>
        <div>
          <dt>Humidity</dt>
          <dd>{current.humidity}%</dd>
        </div>
        <div>
          <dt>Wind</dt>
          <dd>{Math.round(current.windSpeed)} km/h</dd>
        </div>
      </dl>
    </section>
  )
}
