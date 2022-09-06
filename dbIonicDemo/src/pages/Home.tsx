import { IonButton, IonContent, IonHeader, IonImg, IonInput, IonItem, IonLabel, IonList, IonPage, IonSelect, IonSelectOption, IonThumbnail, IonTitle, IonToolbar } from '@ionic/react';
import { useEffect, useState } from 'react';
import ExploreContainer from '../components/ExploreContainer';
import { getAllCustomers, insertCustomer } from '../databaseHandler';
import { Customer } from '../models/Customer';
import './Home.css';

const Home: React.FC = () => {
  const [name, setName] = useState('')
  const [language, setLanguages] = useState<string[]>([])
  const [picture, setPicture] = useState('')
  const [allCustomers, setAllCustomers] = useState<Customer[]>([])

  const fetchData = async () => {
    const data = await getAllCustomers()
    setAllCustomers(data)
  }

  useEffect(() => {
    fetchData()
  }, [])

  const saveHandler = async () => {
    const newCus: Customer = { name: name, languages: language, picture: picture }
    const id = await insertCustomer(newCus)
    alert(id)
  }
  return (
    <IonPage>
      <IonHeader>
        <IonToolbar color="secondary">
          <IonTitle>Customer management!</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent>
        <IonItem>
          <IonLabel>Name</IonLabel>
          <IonInput onIonChange={e => setName(e.detail.value!)}></IonInput>
        </IonItem>
        <IonItem>
          <IonLabel>Language</IonLabel>
          <IonSelect multiple onIonChange={e => setLanguages(e.detail.value!)}>
            <IonSelectOption value="The UK">The UK</IonSelectOption>
            <IonSelectOption value="The USA">The USA</IonSelectOption>
            <IonSelectOption value="Spain">Spain</IonSelectOption>
          </IonSelect>
        </IonItem>
        <IonItem>
          <IonLabel>Picture</IonLabel>
          <IonInput onIonChange={e => setPicture(e.detail.value!)}></IonInput>
        </IonItem>
        <IonButton onClick={saveHandler} expand='block' class='ion-margin'>Save</IonButton>
        {allCustomers &&
          <IonList>
            {allCustomers.map(c =>
              <IonItem key={c.id}>
                <IonThumbnail slot="start">
                  <IonImg src={c.picture} />
                </IonThumbnail>
                <IonLabel>
                  {c.name} 
                  <IonLabel>{c.languages}</IonLabel>      
                </IonLabel>               
              </IonItem>
            )}
          </IonList>
        }

      </IonContent>

    </IonPage>
  );
};

export default Home;
