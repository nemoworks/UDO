import { useEffect } from 'react'

export default function Page({
  match: {
    params: { schemaName, id },
  },
}) {
  useEffect(() => {}, [])

  return (
    <div>
      {schemaName},{id}
    </div>
  )
}
