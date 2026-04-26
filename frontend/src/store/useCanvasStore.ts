import { create } from 'zustand';

interface canvasState {
    isConnected: boolean;
    setConnected: (status: boolean) => void;

    // TODO : add shapes and users

}

export const useCanvasStore = create<canvasState>((set) => ({
    isConnected: false,
    setConnected: (status) => set({ isConnected: status })

}));




