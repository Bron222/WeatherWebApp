import { useState } from 'react'
import SearchBox from './components/SearchBox'
import CurrentConditions from './components/CurrentConditions'
import Forecast from './components/Forecast'
import { useWeather } from './hooks/useWeather'
import './App.css'

export default function App() {
  const [place, setPlace] = useState(null)
  const { data: weather, isLoading, isError, error, refetch } = useWeather(place)

  return (
    <main className="app">
      <header>
        <h1>Weather</h1>
      </header>

      <SearchBox onSelect={setPlace} />

      {!place && <p className="status">Search for a city to see its forecast.</p>}

      {isLoading && <p className="status">Loading forecast…</p>}

      {isError && (
        <div className="status error">
          <p>{error.message}</p>
          <button type="button" onClick={() => refetch()}>
            Try again
          </button>
        </div>
      )}

      {weather && (
        <>
          <CurrentConditions place={place} current={weather.current} />
          <Forecast days={weather.daily} />
        </>
      )}
    </main>
  )
}
