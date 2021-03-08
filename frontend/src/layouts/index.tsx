import { useState } from 'react'
import { Layout, Menu } from 'antd'
import { history } from 'umi'
import { Icon } from '@/components'
import './index.sass'

const { Sider, Content } = Layout

export default function DefaultLayout({ children }: any) {
  const [collapse, setCollapse] = useState(true)

  return (
    <Layout>
      <Sider
        collapsible
        trigger={null}
        collapsed={collapse}
        width="170px"
        collapsedWidth="50px"
      >
        <div className="logo" onClick={() => setCollapse(!collapse)}>
          <Icon type="icongem" />
          <span className="text">DB Manager</span>
        </div>

        <Menu theme="dark" mode="inline" defaultSelectedKeys={['2']}>
          <Menu.Item
            key="Author"
            icon={<Icon type="iconauthor" />}
            onClick={() => history.push('/author')}
          >
            作者管理
          </Menu.Item>
          <Menu.Item
            key="Paper"
            icon={<Icon type="iconpaper" />}
            onClick={() => history.push('/paper')}
          >
            论文管理
          </Menu.Item>
          <Menu.Item
            key="Schema"
            icon={<Icon type="iconschema" />}
            onClick={() => history.push('/schema')}
          >
            Schema管理
          </Menu.Item>
          <Menu.Item
            key="Object"
            icon={<Icon type="iconobject" />}
            onClick={() => history.push('/object')}
          >
            数字对象管理
          </Menu.Item>
        </Menu>
      </Sider>
      <Content>{children}</Content>
    </Layout>
  )
}
