import { useEffect, useState } from 'react'
import axios from 'axios'
import { message } from 'antd'
import XForm from '@perish/react-xform'
import { JSONEditor, Card, Icon } from '@/components'
import { composer, extractor, transformer } from '@/components/XForm'
import './index.sass'

export default function Page() {
  useEffect(() => {}, [])

  return <div className="page schema-all container"></div>
}
