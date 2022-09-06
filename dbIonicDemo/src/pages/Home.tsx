import { IonButton, IonContent, IonHeader, IonInput, IonItem, IonLabel, IonPage, IonSelect, IonSelectOption, IonTitle, IonToolbar } from '@ionic/react';
import ExploreContainer from '../components/ExploreContainer';
import './Home.css';

const Home: React.FC = () => {
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
          <IonInput></IonInput>
        </IonItem>
        <IonItem>
           <IonLabel>Language</IonLabel>
           <IonSelect multiple>
              <IonSelectOption value="The UK">The UK</IonSelectOption>
              <IonSelectOption value="The USA">The USA</IonSelectOption>
              <IonSelectOption value="Spain">Spain</IonSelectOption>
            </IonSelect>
        </IonItem>
        <IonItem>
          <IonLabel>Picture</IonLabel>
          <IonInput></IonInput>
        </IonItem>
        <IonButton expand='block' class='ion-margin'>Save</IonButton>
      </IonContent>
    </IonPage>
  );
};

export default Home;
