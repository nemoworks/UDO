import React, { useEffect, useRef } from 'react'
import JE, { JSONEditorOptions } from 'jsoneditor'
import 'jsoneditor/dist/jsoneditor.css'
import './index.sass'

interface Props {
  json?: any
  mode: string
  hideMenu?: boolean
  onChange?: Function
}

export default function JSONEditor(props: Props) {
  const containerRef = useRef<any>(null)
  const editor = useRef<any>(null)

  const options = {
    mode: props.mode,
    mainMenuBar: props.hideMenu === undefined || props.hideMenu === false,
    onChange: function () {
      try {
        const json = editor.current.get()
        props.onChange && props.onChange(json)
      } catch (err) {}
    },
  } as JSONEditorOptions

  useEffect(() => {
    editor.current = new JE(containerRef.current, options, null)
  }, [])

  useEffect(() => {
    if (
      editor.current.get() === null ||
      editor.current.getText() === '{}' ||
      props.mode === 'view'
    )
      editor.current.set(props.json)
  }, [props.json])

  return <div ref={containerRef} className="component JSONEditor container" />
}
