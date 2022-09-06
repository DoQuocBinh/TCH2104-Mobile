import { IonButton, IonContent, IonHeader, IonInput, IonItem, IonLabel, IonPage, IonSelect, IonSelectOption, IonTitle, IonToolbar } from '@ionic/react';
import { useState } from 'react';
import ExploreContainer from '../components/ExploreContainer';
import { insertCustomer } from '../databaseHandler';
import { Customer } from '../models/Customer';
import './Home.css';

const Home: React.FC = () => {
  const [name,setName] = useState('')
  const [language,setLanguages] = useState<string[]>([])
  const [picture,setPicture] = useState('')
  const saveHandler = async ()=>{
     const newCus : Customer = {name:name,languages:language,picture:picture}
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
          <IonInput onIonChange={e=>setName(e.detail.value!)}></IonInput>
        </IonItem>
        <IonItem>
           <IonLabel>Language</IonLabel>
           <IonSelect multiple onIonChange={e=>setLanguages(e.detail.value!)}>
              <IonSelectOption value="The UK">The UK</IonSelectOption>
              <IonSelectOption value="The USA">The USA</IonSelectOption>
              <IonSelectOption value="Spain">Spain</IonSelectOption>
            </IonSelect>
        </IonItem>
        <IonItem>
          <IonLabel>Picture</IonLabel>
          <IonInput onIonChange={e=>setPicture(e.detail.value!)}></IonInput>
        </IonItem>
        <IonButton onClick={saveHandler} expand='block' class='ion-margin'>Save</IonButton>
      </IonContent>
    </IonPage>
  );
};

export default Home;
