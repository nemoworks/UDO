import {__depth__} from '../utils'
import HOC from './HOC'

function Info({schema}) {
  return <div className="Info">{schema.data}</div>
}

export default HOC(Info)
